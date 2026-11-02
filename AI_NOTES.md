# AI Context – WKND Flights

## Project Overview

WKND Flights is an AEM technical exercise focused on building reusable AEM components and reproducing the required page structure from the assignment brief.

The assignment brief is the primary source of truth for requirements. AI assistance should support implementation without replacing the requirements or project-specific decisions.

## Development Guidelines

- Follow the requirements and acceptance criteria in the assignment brief.
- Use the existing project structure and naming conventions.
- Build reusable AEM components rather than duplicating markup.
- Use HTL for component rendering.
- Use Sling Models for component logic and data access where appropriate.
- Use Granite UI / Coral UI for authoring dialogs.
- Keep component styles and JavaScript in the appropriate client libraries.
- Avoid hardcoding content that should be authorable.
- Use meaningful names for Java classes, methods, dialog fields, and CSS classes.
- Keep implementation simple, readable, and maintainable.
- Add appropriate tests for Sling Models and component logic.
- Verify changes through local deployment and AEM authoring wherever possible.
- Do not introduce unnecessary dependencies or additional features outside the assignment scope.
- Update README.md and AUTHORING.md to explain implementation and authoring steps.

## AI Usage Guidelines

- Use AI for implementation guidance, code review, troubleshooting, and documentation support.
- Review AI-generated code before adding it to the repository.
- Validate generated code against the assignment requirements and the existing project structure.
- Do not assume that AI-generated code is correct without testing.
- Do not include credentials, access tokens, or other sensitive information in AI prompts.

## Tool Disclosure

ChatGPT was used for development assistance and documentation support.


# AI Notes – WKND Flights

## 1. AI Tools Used

**ChatGPT**

ChatGPT was used as a development assistant during the WKND Flights AEM technical exercise.

The main use cases were:

- Understanding the assignment requirements and breaking them into implementation tasks.
- Reviewing HTL, Sling Models, authoring dialogs, and component structure.
- Preparing README.md and AUTHORING.md documentation.

## 2. One Thing AI Got Wrong and How I Caught It

AI-generated implementation guidance can sometimes assume component properties, dialog field names, or project structures that do not exactly match the existing codebase.

For example, AI may suggest a Sling Model property name that does not match the actual authoring dialog.

I would catch this by checking the dialog definitions, Sling Model annotations, and HTL property references together. I would then validate the implementation through local deployment and testing.

The lesson is that AI-generated code must be checked against the actual project rather than copied without verification.

## 3. One AI Suggestion I Rejected and Why

I would reject an AI suggestion to introduce additional abstractions, dependencies, or features that are not required by the assignment.

For a technical exercise, unnecessary complexity can make the solution harder to understand, test, and maintain.

I prefer to follow the assignment requirements and the existing project structure, implementing only what is needed.

## 4. What I Would Set Up Differently for a Team on a Client Project

For a client project, I would establish a shared AI development workflow with the team.

I would:

- Maintain a common AI context and project rules file.
- Document approved tools and their intended use.
- Define coding standards and AEM-specific implementation guidelines.
- Require code review for AI-assisted changes.
- Validate AI-generated code through automated tests and CI/CD checks.
- Avoid sharing client credentials, confidential data, or sensitive business information with AI tools.
- Maintain clear documentation of AI-assisted implementation decisions.

This would help the team use AI consistently while maintaining code quality, security, and accountability.
