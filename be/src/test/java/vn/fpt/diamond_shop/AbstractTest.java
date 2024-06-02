package vn.fpt.diamond_shop;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractTest {
    /**
     * Convert an object to a JSON string.
     *
     * @param obj the object to convert
     * @return the JSON string representation of the object
     */
    protected static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
