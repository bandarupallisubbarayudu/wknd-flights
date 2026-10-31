
# Teaser Component – Authoring Guide

## Overview

The Teaser component is used to display an image alongside a title, introductory text, rich-text description, and optional links.

Authors can configure the image position, heading level, and additional navigation options.

## Authorable Fields

| Field | Description |
|---|---|
| Anchor ID | Enter a unique ID for the Teaser component. This can be used to link directly to the component from another section or page. |
| Title | Enter the heading displayed in the Teaser component. |
| Intro Text | Enter a short introductory text displayed below the title. |
| Description | Enter the detailed content using the Rich Text Editor. Supports paragraphs, lists, and inline links. |
| Image | Select the image from AEM DAM. |
| Alternative Text | Enter a description of the teaser image. |
| Decorative | Select this option if the image is decorative. |
| Links | Add one or more links to display below the description. |
| Open in New Tab | Select this option to open configured teaser links in a new browser tab. |
| Show Back to Top | Select this option to display a Back to Top link below the Teaser component. |
| Heading Level | Select the heading level for the Teaser title (H1 to H6) using the component style configuration. |

## Link Multifield

The Teaser component supports multiple links through the `links` multifield.

| Field | Description |
|---|---|
| Link Text | Enter the text displayed for the link. |
| Link URL | Enter the destination URL or page path. |

## Authoring Example

| Field | Example Value |
|---|---|
| Title | Joey Club |
| Intro Text | Join our club designed just for kids. |
| Description | Meet our Joey Club family, aiming to make flying fun for our little travellers. |
| Image | `/content/dam/wknd-flights/site/joey-club-with-logo.jpg` |
| Alternative Text | Animated image of Joey Club animal characters |
| Anchor ID | `joey-club` |
| Open in New Tab | `true` |
| Show Back to Top | `true` |

| Link Text | Link URL |
|---|---|
| Find out about the Joey Club | `/content/wknd-flights/us/en/joey-club` |

## Authoring Steps

1. Open the page in AEM Edit mode.
2. Add or select the Teaser component.
3. Enter the Anchor ID, title, and intro text.
4. Enter the description using the Rich Text Editor.
5. Select the image from DAM and provide the alternative text.
6. Configure the Decorative option if required.
7. Add links using the Links multifield.
8. Configure Open in New Tab and Show Back to Top as required.
9. Select the appropriate heading level in the component style configuration.
10. Preview the page and verify the content.
11. Publish the page.
