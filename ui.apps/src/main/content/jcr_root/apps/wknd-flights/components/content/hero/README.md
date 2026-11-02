
# Hero Component

## Overview

The Hero component is a custom AEM component developed for the WKND Flights project.

It displays the primary page heading, a hero image, introductory text, and optional in-page navigation links.

The component is designed to introduce the page content and provide users with quick navigation to important sections.

## Component Details

| Property | Value |
|---|---|
| Component Title | Hero Component |
| Component Group | WKND Flights - Content |
| Component Abbreviation | HC |
| Component Path | `/apps/wknd-flights/components/content/hero` |
| Sling Model | `com.adobe.aem.wknd.flights.core.models.HeroModel` |
| HTL Script | `hero.html` |
| Dialog | `_cq_dialog/.content.xml` |

## Features

- Displays a configurable page title.
- Displays an optional hero image.
- Supports introductory text.
- Supports image alternative text.
- Supports decorative image configuration.
- Supports DAM metadata description as an alternative-text fallback.
- Supports multiple in-page navigation links.
- Generates navigation links using configured section IDs.
- Provides responsive styling for desktop, tablet, and mobile devices.
- Displays an authoring placeholder when the title is not configured.

## Component Structure

```text
/apps/wknd-flights/components/content/hero
    ├── .content.xml
    ├── hero.html
    └── _cq_dialog
        └── .content.xml
```

The Sling Model is implemented in the Core module:

```text
core/src/main/java/com/adobe/aem/wknd/flights/core/models
    ├── HeroModel.java
    └── HeroNavLink.java
```

The component styling is implemented in the frontend module:

```text
ui.frontend/src/main/webpack/components/content/_hero.scss
```

## Sling Model

The Hero component uses the `HeroModel` Sling Model.

The model adapts from a Sling Resource and uses optional injection for its properties.

### Model Properties

| Property | Type | Description |
|---|---|---|
| `title` | String | Main hero heading |
| `image` | String | Hero image path |
| `altText` | String | Image alternative text |
| `introText` | String | Introductory content |
| `decorative` | Boolean | Indicates whether the image is decorative |
| `navLinks` | List of HeroNavLink | In-page navigation links |

### Alternative Text Resolution

The model resolves the image alternative text using the following logic:

1. If the image is decorative, return an empty string.
2. If explicit alt text is configured, return the trimmed value.
3. If the image path is missing, return an empty string.
4. If the DAM asset does not exist, return an empty string.
5. If the DAM metadata resource does not exist, return an empty string.
6. Otherwise, return the trimmed `dc:description` metadata value.

The metadata is read from:

```text
/content/dam/.../jcr:content/metadata
```

The metadata property used is:

```text
dc:description
```

## Navigation Links

The Hero component supports a composite multifield named `navLinks`.

Each navigation item contains:

| Property | Description |
|---|---|
| `linkText` | Text displayed in the navigation |
| `linkId` | Target section ID |

The HTL generates the navigation URL using the configured link ID:

```html
<a href="#${navLink.linkId}">
    ${navLink.linkText}
</a>
```

The target section must contain a matching HTML ID.

Example:

```html
<section id="before-your-flight">
    ...
</section>
```

A navigation item configured with `linkId` equal to `before-your-flight` links to that section.

## HTL Rendering

The HTL script uses the Hero Sling Model:

```html
<sly data-sly-use.hero="com.adobe.aem.wknd.flights.core.models.HeroModel" />
```

The component renders:

- Main heading
- Hero image
- Introductory text
- In-page navigation

The main Hero markup is rendered only when the title is configured.

When the title is unavailable, the component displays an authoring placeholder.

## Styling

The component styles are maintained in:

```text
ui.frontend/src/main/webpack/components/content/_hero.scss
```

The styling includes:

- Maximum content width of 1280px
- Responsive heading typography
- Hero image layout
- Introductory content layout
- Navigation layout
- Responsive navigation grid
- Mobile-specific adjustments
- Hover and focus styles for navigation links

## Accessibility

The component supports the following accessibility considerations:

- Uses an `h1` element for the primary hero heading.
- Uses a navigation landmark for in-page links.
- Uses configured alternative text for meaningful images.
- Supports decorative images with empty alternative text.
- Provides visible keyboard focus styling for navigation links.
- Uses descriptive navigation link text configured by the author.

Authors should ensure that navigation target IDs are unique and match the IDs of the corresponding page sections.

## Authoring

The component provides the following dialog fields:

### Properties Tab

- Title
- Intro Text
- Image
- Alt Text
- Decorative Image checkbox

### Nav Links Tab

- Link Text
- Link ID

Refer to `AUTHORING.md` for the detailed authoring instructions.

## Testing

The Hero Sling Model can be tested using JUnit 5 and AEM Mocks.

Test cases should cover:

- Hero property injection
- Alternative-text resolution
- Decorative image handling
- DAM metadata fallback
- Missing DAM asset handling
- Missing metadata handling
- Navigation link injection
- Empty navigation links

Run the Hero model unit tests using:

```bash
mvn -pl core -Dtest=HeroModelTest clean test
```

## Assumptions and Limitations

- The component expects the author to configure the primary title.
- The hero image is optional.
- The introductory text is optional.
- Navigation links are optional.
- Navigation target IDs must be configured to match IDs on the page.
- The component does not create or manage the target page sections.
- The component does not automatically generate navigation IDs.
- The component uses DAM `dc:description` as a fallback only when explicit alt text is not provided.
- The component currently renders the primary heading as an `h1`.
