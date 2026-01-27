package com.mailctl.recipients;

import java.util.*;

public class EmailRecipients {

    /**
     * Resolve recipients from comma-separated lists provided via CLI.
     *
     * @param emailsCommaSeparated    Comma-separated email addresses
     * @param namesCommaSeparated     Comma-separated recipient names (optional)
     * @param companiesCommaSeparated Comma-separated company names (optional)
     * @return List of maps, each containing "email", "name", and "company"
     */
    public List<Map<String, String>> resolveFromLists(
            String emailsCommaSeparated,
            String namesCommaSeparated,
            String companiesCommaSeparated
    ) {
        if (emailsCommaSeparated == null || emailsCommaSeparated.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> emails = Arrays.asList(emailsCommaSeparated.split(","));
        List<String> names = namesCommaSeparated != null && !namesCommaSeparated.isEmpty()
                ? Arrays.asList(namesCommaSeparated.split(","))
                : new ArrayList<>();
        List<String> companies = companiesCommaSeparated != null && !companiesCommaSeparated.isEmpty()
                ? Arrays.asList(companiesCommaSeparated.split(","))
                : new ArrayList<>();

        List<Map<String, String>> recipients = new ArrayList<>();
        for (int i = 0; i < emails.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("email", emails.get(i).trim());
            map.put("name", i < names.size() ? names.get(i).trim() : "");      // default empty if not enough names
            map.put("company", i < companies.size() ? companies.get(i).trim() : ""); // default empty if not enough companies
            recipients.add(map);
        }

        return recipients;
    }
}
