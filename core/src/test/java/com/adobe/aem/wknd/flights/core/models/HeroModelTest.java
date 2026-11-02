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
class HeroModelTest {

    private final AemContext context = AppAemContext.newAemContext();
    private static final String HERO_PATH = "/content/wknd-flights/jcr:content/root/hero";

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(HeroModel.class, HeroNavLink.class);
    }

    private HeroModel createHeroModel() {
        Resource resource = context.resourceResolver().getResource(HERO_PATH);
        assertNotNull(resource);
        HeroModel model = resource.adaptTo(HeroModel.class);
        assertNotNull(model);
        return model;
    }

    @Test
    void testHeroProperties() {
        context.create().resource(HERO_PATH, "title", "Flying with infants and children", "image", "/content/dam/wknd-flights/hero.jpg", "altText", "Family travelling with children", "introText", "Information for travelling families", "decorative", false);
        HeroModel model = createHeroModel();
        assertEquals("Flying with infants and children", model.getTitle());
        assertEquals("/content/dam/wknd-flights/hero.jpg", model.getImage());
        assertEquals("Family travelling with children", model.getAltText());
        assertEquals("Information for travelling families", model.getIntroText());
        assertFalse(model.isDecorative());
    }

    @Test
    void testExplicitAltTextIsTrimmed() {
        context.create().resource(HERO_PATH, "image", "/content/dam/wknd-flights/hero.jpg", "altText", "  Family travelling together  ", "decorative", false);
        HeroModel model = createHeroModel();
        assertEquals("Family travelling together", model.getAltText());
    }

    @Test
    void testDecorativeImageReturnsEmptyAltText() {
        context.create().resource(HERO_PATH, "image", "/content/dam/wknd-flights/hero.jpg", "altText", "Family travelling", "decorative", true);
        HeroModel model = createHeroModel();
        assertTrue(model.isDecorative());
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextFallsBackToDamDescription() {
        String assetPath = "/content/dam/wknd-flights/hero.jpg";
        context.create().resource(assetPath + "/jcr:content/metadata", "dc:description", "  Family boarding an aircraft  ");
        context.create().resource(HERO_PATH, "image", assetPath, "decorative", false);
        HeroModel model = createHeroModel();
        assertEquals("Family boarding an aircraft", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenDamAssetDoesNotExist() {
        context.create().resource(HERO_PATH, "image", "/content/dam/wknd-flights/missing.jpg", "decorative", false);
        HeroModel model = createHeroModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenMetadataDoesNotExist() {
        String assetPath = "/content/dam/wknd-flights/hero.jpg";
        context.create().resource(assetPath);
        context.create().resource(HERO_PATH, "image", assetPath, "decorative", false);
        HeroModel model = createHeroModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenImageIsNotConfigured() {
        context.create().resource(HERO_PATH, "decorative", false);
        HeroModel model = createHeroModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testAltTextReturnsEmptyWhenAltTextAndDescriptionAreMissing() {
        String assetPath = "/content/dam/wknd-flights/hero.jpg";
        context.create().resource(assetPath + "/jcr:content/metadata");
        context.create().resource(HERO_PATH, "image", assetPath, "altText", "", "decorative", false);
        HeroModel model = createHeroModel();
        assertEquals("", model.getAltText());
    }

    @Test
    void testHeroNavigationLinks() {
        context.create().resource(HERO_PATH, "title", "Flying with infants and children");
        context.create().resource(HERO_PATH + "/navLinks/item0", "linkText", "Before your flight", "linkId", "before-your-flight");
        context.create().resource(HERO_PATH + "/navLinks/item1", "linkText", "At the airport", "linkId", "at-the-airport");
        HeroModel model = createHeroModel();
        assertNotNull(model.getNavLinks());
        assertEquals(2, model.getNavLinks().size());
        HeroNavLink firstLink = model.getNavLinks().get(0);
        HeroNavLink secondLink = model.getNavLinks().get(1);
        assertEquals("Before your flight", firstLink.getLinkText());
        assertEquals("before-your-flight", firstLink.getLinkId());
        assertEquals("At the airport", secondLink.getLinkText());
        assertEquals("at-the-airport", secondLink.getLinkId());
    }

    @Test
    void testEmptyNavigationLinks() {
        context.create().resource(HERO_PATH);
        HeroModel model = createHeroModel();
        assertTrue(model.getNavLinks() == null || model.getNavLinks().isEmpty());
    }
}