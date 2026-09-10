package com.devops.access;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccessEligibilityServiceTest {

    private final AccessEligibilityService service = new AccessEligibilityService();

    @Test
    public void testNormalEligibleEmployee() throws Exception {
        Employee emp = new Employee("E101", "Alice", 25, "IT", "ACTIVE", 4, true);
        AccessEligibilityService.EvaluationResult result = service.evaluateAccess(emp, 3, true);
        assertEquals(AccessEligibilityService.Status.ELIGIBLE, result.getStatus());
        assertTrue(result.getReasons().isEmpty());
    }

    @Test
    public void testBoundaryAge21Eligible() throws Exception {
        Employee emp = new Employee("E102", "Bob", 21, "HR", "ACTIVE", 3, true);
        AccessEligibilityService.EvaluationResult result = service.evaluateAccess(emp, 2, false);
        assertEquals(AccessEligibilityService.Status.ELIGIBLE, result.getStatus());
    }

    @Test
    public void testMultipleFailureScenario() throws Exception {
        Employee emp = new Employee("E103", "Charlie", 19, "MARKETING", "INACTIVE", 1, false);
        AccessEligibilityService.EvaluationResult result = service.evaluateAccess(emp, 3, true);

        assertEquals(AccessEligibilityService.Status.NOT_ELIGIBLE, result.getStatus());
        // Verify multiple failure reasons are caught in a single run
        assertTrue(result.getReasons().size() >= 4);
    }

    @Test
    public void testInvalidInputConstructorException() {
        assertThrows(InvalidEmployeeDataException.class, () -> {
            new Employee("", "David", -5, "IT", "ACTIVE", 2, true);
        });
    }
}
