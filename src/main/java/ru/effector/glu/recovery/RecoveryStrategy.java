package ru.effector.glu.recovery;

import ru.effector.glu.model.ModelEntry;

/**
 * @author Alexandr Kolosov
 * @since 31.07.2015
 */
public interface RecoveryStrategy {

    String getRecoveryAgentForEntry(ModelEntry modelEntry);

}
