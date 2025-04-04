package com.example.user.service;

import com.example.user.enums.GlowChangeType;
import com.example.user.model.UserGlow;
import com.example.user.model.UserGlowLog;
import com.example.user.repository.UserGlowLogRepository;
import com.example.user.repository.UserGlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGlowService {

    @Autowired
    private UserGlowRepository userGlowRepository;

    @Autowired
    private UserGlowLogRepository glowLogRepository;

    public void addRecordPoint(Long userId) {
        updateGlow(userId, 1, GlowChangeType.RECORD, "记录日记");
    }

    public void addDialogPointIfThreshold(Long userId, int newDialogCount) {
        UserGlow glow = userGlowRepository.findByUserId(userId).orElseGet(() -> initGlow(userId));
        int oldDialog = glow.getTotalDialogues();
        int newTotalDialog = oldDialog + newDialogCount;
        int oldPoint = oldDialog / 5;
        int newPoint = newTotalDialog / 5;
        int gained = newPoint - oldPoint;

        if (gained > 0) {
            updateGlow(userId, gained, GlowChangeType.DIALOG, "对话达标：" + gained + " 点");
        }

        glow.setTotalDialogues(newTotalDialog);
        userGlowRepository.save(glow);
    }

    private void updateGlow(Long userId, int delta, GlowChangeType type, String desc) {
        UserGlow glow = userGlowRepository.findByUserId(userId).orElseGet(() -> initGlow(userId));
        glow.setGlowPoint(glow.getGlowPoint() + delta);
        glow.setTotalRecords(glow.getTotalRecords() + (type == GlowChangeType.RECORD ? 1 : 0));
        glow.setGlowLevel(calculateGlowLevel(glow.getGlowPoint()));
        userGlowRepository.save(glow);

        UserGlowLog log = new UserGlowLog();
        log.setUserId(userId);
        log.setChangeType(type);
        log.setChangeValue(delta);
        log.setCurrentTotal(glow.getGlowPoint());
        log.setDescription(desc);
        glowLogRepository.save(log);
    }

    private int calculateGlowLevel(int point) {
        int[] levels = {1, 5, 10, 20, 40};
        for (int i = levels.length - 1; i >= 0; i--) {
            if (point >= levels[i]) {
                return i + 1 + (point - levels[i]) / 20;
            }
        }
        return 0;
    }

    private UserGlow initGlow(Long userId) {
        UserGlow glow = new UserGlow();
        glow.setUserId(userId);
        glow.setGlowPoint(0);
        glow.setTotalDialogues(0);
        glow.setTotalRecords(0);
        glow.setGlowLevel(0);
        return glow;
    }

    public UserGlow getUserGlow(Long userId) {
        return userGlowRepository.findByUserId(userId)
                .orElseGet(() -> initGlow(userId));
    }
}
