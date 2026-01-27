package com.mailctl.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import com.mailctl.app.SendWorkflow;

@Command(
    name = "send",
    description = "Send emails to one or multiple recipients"
)
public class SendCommand implements Runnable {

    @Option(names = {"-t", "--to"}, required = true, description = "Recipient email(s), comma separated")
    private String recipients;

    @Option(names = {"-s", "--subject"}, required = true, description = "Subject of the email")
    private String subject;

    @Option(names = {"-b", "--body"}, description = "Body of the email (plain text)")
    private String body;

    @Option(names = {"-n", "--names"}, description = "Optional recipient names, comma separated")
    private String names;

    @Option(names = {"-c", "--company"}, description = "Optional recipient company names, comma separated")
    private String companies;

    @Option(names = {"-a", "--attachment"}, description = "Optional PDF file(s), comma separated")
    private String attachments;

    @Option(names = {"--rate"}, description = "Optional rate limit (emails/sec)")
    private int rate = 0;

    @Override
    public void run() {
        // Simply call the workflow with all the arguments
        SendWorkflow workflow = new SendWorkflow();
        workflow.execute(
            recipients,
            names,
            companies,
            subject,
            body,
            attachments,
            rate
        );
    }
}
