package com.adobe.aem.wknd.flights.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class HeroModel {
    @ValueMapValue
    private String title;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String altText;

    @ValueMapValue
    private String introText;

    @ValueMapValue
    private boolean decorative;

    @ChildResource(name = "navLinks")
    private List<HeroNavLink> navLinks;

    @SlingObject
    private ResourceResolver resourceResolver;

    private String resolvedAltText;

    @PostConstruct
    protected void init() {
        resolvedAltText = resolveAltText();
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getAltText() {
        return resolvedAltText;
    }

    public String getIntroText() {
        return introText;
    }

    public boolean isDecorative() {
        return decorative;
    }

    public List<HeroNavLink> getNavLinks() {
        return navLinks;
    }

    private String resolveAltText() {
        if (decorative) {
            return "";
        }
        if (altText != null && !altText.trim().isEmpty()) {
            return altText.trim();
        }
        if (image == null || image.trim().isEmpty() || resourceResolver == null) {
            return "";
        }
        Resource assetResource = resourceResolver.getResource(image);
        if (assetResource == null) {
            return "";
        }
        Resource metadataResource = assetResource.getChild("jcr:content/metadata");
        if (metadataResource == null) {
            return "";
        }
        ValueMap metadata = metadataResource.getValueMap();
        String damDescription = metadata.get("dc:description", String.class);
        return damDescription != null ? damDescription.trim() : "";
    }
}