package com.mailctl.content;

//import java.util.Map;

public class ContentBuilder {

    private String body; // user-provided body, optional

    // Constructor accepts user-provided body (null if not provided)
    public ContentBuilder(String body) {
        this.body = body;
    }

    /**
     * Build the final email body for a recipient.
     * If body is provided by user, use it. Otherwise, use default template.
     */
    public String build(String name, String company) {
        if (body != null && !body.isEmpty()) {
            return body; // user-specified body overrides template
        }

        // Default template with placeholders replaced
        return "Hi " + name + ",\n\n" +
                "I am reaching out from the engineering team at " + company + 
                "Thank you for your time and consideration.\n\n" +
                
                
    }
}

