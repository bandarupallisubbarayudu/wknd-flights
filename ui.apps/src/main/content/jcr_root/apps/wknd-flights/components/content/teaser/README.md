
# Teaser Component

## Overview

The Teaser component is a reusable AEM component used to present promotional or informational content with an image, heading, descriptive text, and call-to-action links.

It is designed to support flexible content authoring while keeping presentation logic in HTL and component behavior in the Sling Model.

## Component Location

```text
/apps/wknd-flights/components/content/teaser
```

## Implementation

| Area | Description |
|---|---|
| Component | Teaser |
| Sling Model | `TeaserModel` |
| HTL | `teaser.html` |
| Dialog | `_cq_dialog/.content.xml` |
| Client-side styling | Teaser component client library |
| Model adaptation | Sling Model adapted from the request |
| Heading level | Configurable through component policy |

## Features

- Displays teaser image and content.
- Supports a configurable heading level.
- Supports introductory and descriptive content.
- Supports CTA links.
- Supports image and content layout variations.
- Supports responsive presentation for desktop, tablet, and mobile.
- Supports accessible image alternative text.
- Uses HTL for rendering and Sling Model for component logic.

## Component Structure

```text
teaser/
├── .content.xml
├── _cq_dialog/
│   └── .content.xml
├── teaser.html
└── clientlibs/
    ├── css/
    └── js/
```

> Update the structure above if the implementation stores client libraries outside the component folder.

## Sling Model

The `TeaserModel` is responsible for exposing the teaser content to HTL.

Typical responsibilities include:

- Reading authored component properties.
- Resolving the image and its alternative text.
- Exposing the teaser title and content.
- Exposing CTA link information.
- Resolving and validating the heading level from the component policy.
- Providing presentation-ready values to the HTL template.

The HTL template should not contain business logic.

## HTL

The `teaser.html` template renders the teaser using values exposed by the Sling Model.

The heading element is selected dynamically using the model's heading level:

```html
<sly data-sly-element="${teaser.headingLevel}"
     class="cmp-teaser__title">
    ${teaser.title}
</sly>
```

The model validates the heading level and falls back to `h2` when the configured value is not valid.

## Dialog

The component dialog provides authoring controls for the teaser content.

Depending on the configured dialog fields, authors can manage:

- Teaser title
- Image
- Image alternative text
- Introductory text
- Description
- CTA links
- Link labels and destinations
- Layout or image-position options

Refer to the component dialog XML for the exact property names and field configuration.

## Component Policy

The component policy is used to configure the heading level.

The model reads the configured heading type and validates that it is one of:

```text
h1, h2, h3, h4, h5, h6
```

If the policy value is missing or invalid, the model uses `h2`.

This allows the component heading level to be controlled according to the page structure and authoring requirements.

## Styling and Responsive Behavior

The Teaser component styling supports:

- Responsive image and content layout.
- Dynamic heading sizes.
- Introductory and descriptive text.
- CTA link presentation.
- Image-right layout variation.
- Tablet and mobile breakpoints.

The component should remain usable across different viewport sizes without requiring authors to create separate desktop and mobile content.

## Accessibility

The component supports accessibility through:

- Semantic heading elements.
- Alternative text for meaningful images.
- Decorative image handling.
- Descriptive CTA labels.
- Responsive content presentation.

Authors should provide meaningful alternative text for informative images. Decorative images should not expose unnecessary text to assistive technologies.

## Testing

The Sling Model should be covered using AEM Mock and JUnit 5.

Recommended test scenarios:

- Model adapts successfully from the request.
- Authored title and content are exposed correctly.
- Image properties are resolved correctly.
- Alternative text is resolved correctly.
- CTA links are exposed correctly.
- Heading policy values are validated.
- Invalid or missing heading levels fall back to `h2`.
- Empty optional fields do not cause rendering errors.

## Requirement Traceability

| Requirement | Implementation |
|---|---|
| Display teaser content | Teaser Sling Model and HTL |
| Configure teaser content | Component dialog |
| Configure heading level | Component policy |
| Render CTA links | Sling Model and HTL |
| Support responsive layout | Component CSS |
| Support image accessibility | Alternative text resolution |
| Validate component behavior | AEM Mock JUnit tests |

## Design Decisions

- **Sling Model:** Keeps component logic separate from rendering.
- **HTL:** Provides safe and maintainable markup.
- **Component policy:** Allows the heading level to be configured consistently.
- **Responsive CSS:** Supports different viewport sizes.
- **Reusable component:** Allows authors to use the teaser across multiple pages.

## Assumptions and Next Steps

- Confirm the final dialog property names against `_cq_dialog/.content.xml`.
- Confirm the final CTA link structure against the Sling Model.
- Add screenshots of the component in authoring and published views.
- Run the component tests and include the verified test results.
