
# Hero Component - Authoring Guide

## Overview

The Hero component is used to display the primary heading, image, introductory text, and optional in-page navigation for a page.

It helps users understand the page content and navigate to important sections.

## Adding the Hero Component

1. Open the page in AEM Author.
2. Enter Edit mode.
3. Select the component container.
4. Open the component side panel.
5. Locate the Hero Component under the WKND Flights - Content component group.
6. Drag and drop the Hero component onto the page.
7. Open the component dialog.
8. Configure the required properties.
9. Save the dialog.

## Component Dialog

The Hero component provides two tabs:

- Properties
- Nav Links

## Properties Tab

### Title

Enter the main heading for the page.

Example:

```text
Flying with infants and children
```

The title is rendered as the primary page heading.

The title is required for the Hero component to render its main content.

### Intro Text

Enter a short introduction to the page.

Example:

```text
Find information about travelling with infants and children.
```

The introductory text is optional.

### Image

Select the hero image from the configured DAM location.

The image path is restricted to:

```text
/content/dam/wknd-flights
```

Select an appropriate image that represents the page content.

The image is optional.

### Alt Text

Enter a meaningful description of the image.

Example:

```text
Family travelling together at the airport
```

The alt text is used to describe the image for users who cannot see it.

If the alt text is not configured, the component attempts to use the DAM asset's `dc:description` metadata.

### Decorative Image

Select the Decorative Image checkbox when the image is used only for visual decoration and does not provide meaningful information.

When the image is decorative, the component returns an empty alternative-text value.

Use this option only for decorative images.

## Nav Links Tab

The Nav Links tab allows authors to configure in-page navigation links.

These links allow users to jump to specific sections of the page.

### Adding Navigation Links

1. Open the Hero component dialog.
2. Select the Nav Links tab.
3. Add a navigation item using the multifield.
4. Enter the Link Text.
5. Enter the Link ID.
6. Add additional links if required.
7. Save the dialog.

### Link Text

Enter the text displayed to the user.

Example:

```text
Before your flight
```

Use clear and descriptive text.

### Link ID

Enter the ID of the target section on the page.

Example:

```text
before-your-flight
```

The target section must have the same ID.

Example target markup:

```html
<section id="before-your-flight">
    ...
</section>
```

The navigation link will be generated as:

```html
<a href="#before-your-flight">
    Before your flight
</a>
```

### Navigation Configuration Example

| Link Text | Link ID |
|---|---|
| Before your flight | before-your-flight |
| At the airport | at-the-airport |
| On board | on-board |

The corresponding page sections must use the same IDs.

## Authoring Best Practices

- Always provide a meaningful title.
- Keep the introductory text concise.
- Select an image relevant to the page content.
- Provide descriptive alt text for meaningful images.
- Use the Decorative Image option only for decorative images.
- Use descriptive navigation link text.
- Ensure navigation IDs match the target section IDs.
- Avoid duplicate IDs on the same page.
- Verify all navigation links in Preview mode.

## Troubleshooting

### Hero component is not visible

Check whether the Title field is configured.

The component's main markup is rendered only when the title is available.

### Image is not displayed

Verify that:

- The image path is configured.
- The selected asset exists.
- The author has permission to access the asset.

### Navigation link does not work

Verify that:

- The Link ID is configured correctly.
- The target section exists.
- The target section ID matches the configured Link ID.
- The target ID is unique on the page.

### Alt text is empty

Check whether:

- The Decorative Image checkbox is selected.
- Explicit alt text is configured.
- The DAM asset has a `dc:description` metadata value.

If the image is decorative, an empty alt text value is expected.

## Example Authoring Configuration

### Properties

| Field | Example Value |
|---|---|
| Title | Flying with infants and children |
| Intro Text | Information for travelling families |
| Image | `/content/dam/wknd-flights/hero.jpg` |
| Alt Text | Family travelling with children |
| Decorative Image | Unchecked |

### Nav Links

| Link Text | Link ID |
|---|---|
| Before your flight | before-your-flight |
| At the airport | at-the-airport |
| On board | on-board |

## Validation Checklist

Before publishing the page, verify:

- [ ] Hero component is placed on the page.
- [ ] Title is configured.
- [ ] Introductory text is correct.
- [ ] Hero image is selected.
- [ ] Alternative text is configured appropriately.
- [ ] Decorative image setting is correct.
- [ ] Navigation links are configured correctly.
- [ ] Navigation IDs match the target sections.
- [ ] Navigation links work in Preview mode.
- [ ] Component layout is verified on desktop and mobile.
