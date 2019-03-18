package com.netcracker.study.general.Exceptions;

import com.netcracker.study.general.Phase;

public class PhaseViolationException extends Exception {
    public PhaseViolationException(Phase currentPhase) {
        super(currentPhase.toString());
    }
}
