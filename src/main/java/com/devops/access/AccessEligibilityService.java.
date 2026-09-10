package com.devops.access;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessEligibilityService {

    private static final List<String> AUTHORIZED_DEPARTMENTS = Arrays.asList("IT", "HR", "FINANCE", "ADMINISTRATION");

    public enum Status {
        ELIGIBLE,
        CONDITIONALLY_ELIGIBLE,
        NOT_ELIGIBLE
    }

    public static class EvaluationResult {
        private final Status status;
        private final List<String> reasons;

        public EvaluationResult(Status status, List<String> reasons) {
            this.status = status;
            this.reasons = reasons;
        }

        public Status getStatus() { return status; }
        public List<String> getReasons() { return reasons; }

        @Override
        public String toString() {
            return "Status: " + status + " | Reasons: " + (reasons.isEmpty() ? "None" : String.join("; ", reasons));
        }
    }

    public EvaluationResult evaluateAccess(Employee emp, int requiredAccessLevel, boolean isConfidential) {
        List<String> rejectionReasons = new ArrayList<>();

        // Age rule: must be at least 21
        if (emp.getAge() < 21) {
            rejectionReasons.add("Employee age is under 21 years (Current: " + emp.getAge() + ").");
        }

        // Department rule: must be IT, HR, Finance, or Administration
        if (emp.getDepartment() == null || !AUTHORIZED_DEPARTMENTS.contains(emp.getDepartment().toUpperCase())) {
            rejectionReasons.add("Department '" + emp.getDepartment() + "' is not authorized.");
        }

        // Status rule: must be ACTIVE
        if (!"ACTIVE".equalsIgnoreCase(emp.getEmploymentStatus())) {
            rejectionReasons.add("Employment status is not ACTIVE.");
        }

        // ID validity rule
        if (!emp.isIdValid()) {
            rejectionReasons.add("Employee ID status is invalid.");
        }

        // Confidential resource clearance level rule
        if (isConfidential && emp.getSecurityClearanceLevel() < requiredAccessLevel) {
            rejectionReasons.add("Security clearance (" + emp.getSecurityClearanceLevel() + 
                                ") is lower than required level (" + requiredAccessLevel + ") for confidential resources.");
        }

        // Collect all failures rather than stopping at first failure
        if (!rejectionReasons.isEmpty()) {
            return new EvaluationResult(Status.NOT_ELIGIBLE, rejectionReasons);
        }

        // Conditionally eligible state for exact clearance level match on sensitive resources
        if (isConfidential && emp.getSecurityClearanceLevel() == requiredAccessLevel) {
            return new EvaluationResult(Status.CONDITIONALLY_ELIGIBLE, List.of("Requires secondary supervisor approval."));
        }

        return new EvaluationResult(Status.ELIGIBLE, new ArrayList<>());
    }
}
