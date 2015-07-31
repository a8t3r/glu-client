package ru.effector.glu.recovery.starategies;

import ru.effector.glu.model.ModelEntry;
import ru.effector.glu.recovery.RecoveryStrategy;

/**
 * @author Alexandr Kolosov
 * @since 31.07.2015
 */
public class SingleAgentStrategy implements RecoveryStrategy {

    private final String agent;

    public SingleAgentStrategy(String agent) {
        this.agent = agent;
    }

    @Override
    public String getRecoveryAgentForEntry(ModelEntry modelEntry) {
        return agent;
    }
}
