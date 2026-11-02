
# Teaser Component — Authoring Guide

## Overview

The Teaser component allows content authors to create a content section containing an image, heading, descriptive content, and call-to-action links.

The component can be used to highlight information, promote content, or direct visitors to another page.

## Component Location

```text
/apps/wknd-flights/components/content/teaser
```

## How to Add the Teaser Component

1. Open the required page in AEM Author.
2. Select **Edit**.
3. Open the component browser.
4. Search for the **Teaser** component.
5. Drag and drop the component into the required container.
6. Open the component dialog.
7. Configure the required fields.
8. Save the dialog.
9. Preview the page.

## Authoring Fields

Use the component dialog to configure the teaser.

| Field | Authoring guidance |
|---|---|
| Title | Enter a meaningful teaser heading. |
| Image | Select the required image from DAM. |
| Alternative text | Provide descriptive text for an informative image. |
| Introductory text | Enter the short introductory content. |
| Description | Enter the detailed teaser content. |
| CTA links | Configure the required call-to-action links. |
| Link label | Use clear, descriptive text for each link. |
| Link destination | Select or enter the destination URL. |
| Layout option | Select the required layout, if available. |

> Refer to the component dialog XML for the exact field labels and available options.

## Heading Level

The teaser heading level is configured through the component policy.

The supported heading levels are:

```text
h1, h2, h3, h4, h5, h6
```

### Recommended usage

- Use `h1` only when appropriate for the page's primary heading.
- Use `h2` for a major page section.
- Use `h3` or lower for nested sections.
- Maintain a logical heading hierarchy throughout the page.

If the heading level is not configured or is invalid, the component falls back to `h2`.

## Image Authoring

1. Open the Teaser dialog.
2. Select the image field.
3. Browse the AEM Digital Asset Management repository.
4. Select the required asset.
5. Provide alternative text when the image conveys meaningful information.
6. Save the dialog.

### Alternative text guidance

- Describe the purpose or meaningful content of the image.
- Avoid repeating the teaser heading unnecessarily.
- Use empty alternative text for decorative images when supported by the component.
- Do not use filenames as alternative text unless they provide meaningful context.

## CTA Link Authoring

The teaser supports call-to-action links.

For each CTA:

1. Add a link using the configured CTA multifield.
2. Enter a meaningful link label.
3. Select or enter the destination.
4. Verify that the link points to the correct page or external destination.
5. Save the dialog.

Use descriptive link labels such as:

- Learn more
- Explore the destination
- Read the full story

Avoid labels such as "Click here" when a more descriptive label is possible.

## Layout and Responsive Preview

After configuring the component:

1. Preview the page in desktop view.
2. Check the image and content alignment.
3. Verify the CTA links.
4. Switch to tablet and mobile previews.
5. Confirm that the content remains readable and the image displays correctly.

The component styling supports responsive behavior and an image-right layout variation.

## Publishing

Before publishing:

- Confirm that the title and description are correct.
- Verify the selected DAM asset.
- Check image alternative text.
- Verify all CTA destinations.
- Confirm the heading hierarchy.
- Preview the component on desktop and mobile.

Publish the page using the standard AEM page activation workflow.

## Troubleshooting

| Issue | Suggested check |
|---|---|
| Teaser is not displayed | Confirm the component is added to the page and the page is saved. |
| Image is missing | Verify the DAM asset path and asset availability. |
| Heading level is unexpected | Check the component policy configuration. |
| CTA is not displayed | Verify the CTA multifield values. |
| Layout looks incorrect | Check the component client library and responsive viewport. |
| Alternative text is missing | Check the authored value and model fallback behavior. |

## Authoring Best Practices

- Keep the teaser title concise.
- Use relevant images.
- Provide meaningful alternative text for informative images.
- Use descriptive CTA labels.
- Avoid adding unnecessary CTA links.
- Maintain a logical heading hierarchy.
- Preview the component at different viewport sizes.
