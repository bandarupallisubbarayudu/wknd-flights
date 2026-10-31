
# Hero Component – Authoring Guide

## Overview

The Hero component is used to display the page title, hero image, introductory text, and optional "On this page" navigation links.

## Authorable Fields

| Field | Description |
|---|---|
| Title | Main heading displayed above the hero image. |
| Image | Select the hero image from AEM DAM. |
| Alternative Text | Provide a description of the hero image. |
| Intro Text | Enter a short introduction describing the page content. |
| Decorative | Select this option if the hero image is decorative. |
| Navigation Links | Add navigation links to allow users to jump to specific sections of the page. |

## Navigation Link Fields

The Hero component supports multiple navigation links through the `navLinks` multifield.

| Field | Description |
|---|---|
| Link Text | Enter the text to display for the navigation link. |
| Link ID | Enter the ID of the target section on the page. |

**Note:** The Link ID must match the ID of the corresponding section on the page.

## Authoring Example

| Field | Example Value |
|---|---|
| Title | Flying with infants and children |
| Image | `/content/dam/wknd-flights/site/a321xlr-onboard-economy-girl-crew-children-kit.jpg` |
| Alternative Text | Female cabin crew member smiling and interacting with a young girl |
| Intro Text | We love having young flyers on board. Whether they're flying with you or travelling alone, here's all you need to know – from booking a flight to the onboard experience. |
| Decorative | `false` |

| Link Text | Link ID |
|---|---|
| Joey Club | `joey-club` |

## Authoring Steps

1. Open the page in AEM Edit mode.
2. Add or select the Hero component.
3. Enter the title and introductory text.
4. Select the hero image from DAM.
5. Enter the alternative text or select the Decorative option.
6. Add navigation links if required.
7. Enter the Link Text and corresponding Link ID.
8. Preview the page and verify the navigation links.
9. Publish the page.
