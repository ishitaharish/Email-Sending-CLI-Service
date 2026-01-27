package com.mailctl.app;

import com.mailctl.recipients.EmailRecipients;
import com.mailctl.content.ContentBuilder;
import com.mailctl.smtp.SmtpService;
import com.mailctl.ratelimit.RateLimiter;
import com.mailctl.logging.SummaryLog;

import java.util.List;
import java.util.Map;

public class SendWorkflow {

    /**
     * Execute the workflow to send emails.
     *
     * @param recipientsList Comma-separated email addresses
     * @param namesList      Comma-separated recipient names
     * @param companiesList  Comma-separated company names
     * @param subject        Email subject
     * @param body           Optional plain-text body (overrides template if provided)
     * @param attachmentsList Comma-separated paths to attachments
     * @param rate           Optional rate limit (emails/sec, currently no-op)
     */
    public void execute(
            String recipientsList,
            String namesList,
            String companiesList,
            String subject,
            String body,
            String attachmentsList,
            int rate
    ) {

        // 1. Resolve recipients from the CLI input
        EmailRecipients resolver = new EmailRecipients();
        List<Map<String, String>> recipientList = resolver.resolveFromLists(recipientsList, namesList, companiesList);
        // Each map: {"email": "...", "name": "...", "company": "..."}

        if (recipientList.isEmpty()) {
            System.out.println("No valid recipients provided. Exiting.");
            return;
        }

        // 2. Initialize content builder (uses default template if body is null)
        ContentBuilder contentBuilder = new ContentBuilder(body);

        // 3. Initialize SMTP service
        SmtpService smtpService = new SmtpService();

        // 4. Initialize rate limiter (currently no-op)
        RateLimiter limiter = new RateLimiter(rate);

        // 5. Initialize logger
        SummaryLog logger = new SummaryLog("sent_summary.txt");

        int sentCount = 0;

        // 6. Loop over all recipients and send emails
        for (Map<String, String> recipient : recipientList) {
            String email = recipient.get("email");
            String name = recipient.get("name");
            String company = recipient.get("company");

            // Build personalized email body
            String finalBody = contentBuilder.build(name, company);

            // Send email
            boolean success = smtpService.sendEmail(email, subject, finalBody, attachmentsList);

            // Log result
            logger.log(email, success);

            if (success) sentCount++;

            // Apply rate limiting (currently does nothing)
            limiter.pause();
        }

        // 7. Print final summary
        logger.printSummary(sentCount, recipientList.size());
    }
}
