package com.adobe.aem.wknd.flights.core.models;

import com.adobe.aem.wknd.flights.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class TeaserModelTest {

    private final AemContext context = AppAemContext.newAemContext();
    private static final String TEASER_PATH = "/content/wknd-flights/jcr:content/root/teaser";

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(TeaserModel.class, TeaserLink.class);
    }

    private TeaserModel createTeaserModel() {
        Resource resource = context.resourceResolver().getResource(TEASER_PATH);
        assertNotNull(resource);
        context.request().setResource(resource);
        TeaserModel model = context.request().adaptTo(TeaserModel.class);
        assertNotNull(model);
        return model;
    }

    @Test
    void testTeaserProperties() {
        context.create().resource(TEASER_PATH, "anchorId", "travel-information", "title", "Travel information", "introText", "Information for travelling families", "description", "Learn more about travelling with children.", "image", "/content/dam/wknd-flights/teaser.jpg", "altText", "Family travelling", "decorative", false, "openInNewTab", false, "showBackToTop", true);
        TeaserModel model = createTeaserModel();
        assertEquals("travel-information", model.getAnchorId());
        assertEquals("Travel information", model.getTitle());
        assertEquals("Information for travelling families", model.getIntroText());
        assertEquals("Learn more about travelling with children.", model.getDescription());
        assertEquals("/content/dam/wknd-flights/teaser.jpg", model.getImage());
        assertEquals("Family travelling", model.getAltText());
        assertFalse(model.isOpenInNewTab());
        assertEquals("_self", model.getTarget());
        assertEquals("", model.getRel());
        assertTrue(model.isShowBackToTop());
    }

    @Test
    void testDefaultHeadingLevel() {
        context.create().resource(TEASER_PATH, "title", "Travel information");
        TeaserModel model = createTeaserModel();
        assertEquals("h2", model.getHeadingLevel());
    }

    @Test
    void testOpenInNewTab() {
        context.create().resource(TEASER_PATH, "openInNewTab", true);
        TeaserModel model = createTeaserModel();
        assertTrue(model.isOpenInNewTab());
        assertEquals("_blank", model.getTarget());
        assertEquals("noopener noreferrer", model.getRel());
    }

    @Test
    void testDefaultTargetAndRel() {
        context.create().resource(TEASER_PATH, "openInNewTab", false);
        TeaserModel model = createTeaserModel();
        assertFalse(model.isOpenInNewTab());
        assertEquals("_self", model.getTarget());
        assertEquals("", model.getRel());
    }

    @Test
    void testShowBackToTopEnabled() {
        context.create().resource(TEASER_PATH, "showBackToTop", true);
        TeaserModel model = createTeaserModel();
        assertTrue(model.isShowBackToTop());
    }

    @Test
    void testShowBackToTopDisabled() {
        context.create().resource(TEASER_PATH, "showBackToTop", false);
        TeaserModel model = createTeaserModel();
        assertFalse(model.isShowBackToTop());
    }

    @Test
    void testTeaserLinks() {
        context.create().resource(TEASER_PATH, "title", "Travel information");
        context.create().resource(TEASER_PATH + "/links/item0", "linkText", "Before your flight", "linkURL", "/content/wknd-flights/before-flight");
        context.create().resource(TEASER_PATH + "/links/item1", "linkText", "At the airport", "linkURL", "/content/wknd-flights/airport.html");
        TeaserModel model = createTeaserModel();
        assertNotNull(model.getLinks());
        assertEquals(2, model.getLinks().size());
        TeaserLink firstLink = model.getLinks().get(0);
        TeaserLink secondLink = model.getLinks().get(1);
        assertEquals("Before your flight", firstLink.getLinkText());
        assertEquals("/content/wknd-flights/before-flight.html", firstLink.getLinkURL());
        assertEquals("At the airport", secondLink.getLinkText());
        assertEquals("/content/wknd-flights/airport.html", secondLink.getLinkURL());
    }

    @Test
    void testExternalLinkIsNotModified() {
        context.create().resource(TEASER_PATH);
        context.create().resource(TEASER_PATH + "/links/item0", "linkText", "External website", "linkURL", "https://www.example.com");
        TeaserModel model = createTeaserModel();
        assertEquals("https://www.example.com", model.getLinks().get(0).getLinkURL());
    }

    @Test
    void testMailToLinkIsNotModified() {
        context.create().resource(TEASER_PATH);
        context.create().resource(TEASER_PATH + "/links/item0", "linkText", "Email us", "linkURL", "mailto:info@example.com");
        TeaserModel model = createTeaserModel();
        assertEquals("mailto:info@example.com", model.getLinks().get(0).getLinkURL());
    }

    @Test
    void testAnchorLinkIsNotModified() {
        context.create().resource(TEASER_PATH);
        context.create().resource(TEASER_PATH + "/links/item0", "linkText", "Jump to section", "linkURL", "#travel-section");
        TeaserModel model = createTeaserModel();
        assertEquals("#travel-section", model.getLinks().get(0).getLinkURL());
    }

    @Test
    void testLinkWithQueryParameter() {
        context.create().resource(TEASER_PATH);
        context.create().resource(TEASER_PATH + "/links/item0", "linkText", "Travel details", "linkURL", "/content/wknd-flights/travel?type=family");
        TeaserModel model = createTeaserModel();
        assertEquals("/content/wknd-flights/travel.html?type=family", model.getLinks().get(0).getLinkURL());
    }

    @Test
    void testLinkWithHash() {
        context.create().resource(TEASER_PATH);
        context.create().resource(TEASER_PATH + "/links/item0", "linkText", "Jump to children section", "linkURL", "/content/wknd-flights/travel#children");
        TeaserModel model = createTeaserModel();
        assertEquals("/content/wknd-flights/travel.html#children", model.getLinks().get(0).getLinkURL());
    }

    @Test
    void testEmptyLinkUrl() {
        context.create().resource(TEASER_PATH);
        context.create().resource(TEASER_PATH + "/links/item0", "linkText", "Empty link", "linkURL", "");
        TeaserModel model = createTeaserModel();
        assertEquals("", model.getLinks().get(0).getLinkURL());
    }

    @Test
    void testEmptyTeaserLinks() {
        context.create().resource(TEASER_PATH);
        TeaserModel model = createTeaserModel();
        assertTrue(model.getLinks() == null || model.getLinks().isEmpty());
    }

    @Test
    void testExplicitAltTextIsTrimmed() {
        context.create().resource(TEASER_PATH, "image", "/content/dam/wknd-flights/teaser.jpg", "altText", "  Family travelling together  ", "decorative", false);
        TeaserModel model = createTeaserModel();
        assertEquals("Family travelling together", model.getAltText());
    }

    @Test
    void testDecorativeImageReturnsEmptyAltText() {
        context.create().resource(TEASER_PATH, "image", "/content/dam/wknd-flights/teaser.jpg", "altText", "Family travelling", "decorative", true);
        TeaserModel model = createTeaserModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextFallsBackToDamDescription() {
        String assetPath = "/content/dam/wknd-flights/teaser.jpg";
        context.create().resource(assetPath + "/jcr:content/metadata", "dc:description", "  Family boarding an aircraft  ");
        context.create().resource(TEASER_PATH, "image", assetPath, "decorative", false);
        TeaserModel model = createTeaserModel();
        assertEquals("Family boarding an aircraft", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenDamAssetDoesNotExist() {
        context.create().resource(TEASER_PATH, "image", "/content/dam/wknd-flights/missing.jpg", "decorative", false);
        TeaserModel model = createTeaserModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenMetadataDoesNotExist() {
        String assetPath = "/content/dam/wknd-flights/teaser.jpg";
        context.create().resource(assetPath);
        context.create().resource(TEASER_PATH, "image", assetPath, "decorative", false);
        TeaserModel model = createTeaserModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenImageIsNotConfigured() {
        context.create().resource(TEASER_PATH, "decorative", false);
        TeaserModel model = createTeaserModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenAltTextAndDescriptionAreMissing() {
        String assetPath = "/content/dam/wknd-flights/teaser.jpg";
        context.create().resource(assetPath + "/jcr:content/metadata");
        context.create().resource(TEASER_PATH, "image", assetPath, "altText", "", "decorative", false);
        TeaserModel model = createTeaserModel();
        assertEquals("", model.getAltText());
    }
}