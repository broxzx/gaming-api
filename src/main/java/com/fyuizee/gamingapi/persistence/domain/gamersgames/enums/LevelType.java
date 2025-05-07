package com.fyuizee.gamingapi.persistence.domain.gamersgames.enums;

public enum LevelType {

    NOOB, PRO, INVINCIBLE;

    public LevelType nextLevel() {
        switch (this) {
            case NOOB -> {
                return PRO;
            }
            case PRO -> {
                return INVINCIBLE;
            }
            default -> throw new IllegalStateException("There are no further levels");
        }
    }

}
