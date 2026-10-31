package com.adobe.aem.wknd.flights.core.models;

import com.day.cq.wcm.api.designer.Style;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TeaserModel {

    @ValueMapValue
    private String anchorId;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String introText;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String altText;

    @ValueMapValue
    private boolean decorative;

    @ChildResource(name = "links")
    private List<TeaserLink> links;

    @ValueMapValue
    private boolean openInNewTab;

    @ValueMapValue
    private boolean showBackToTop;

    @ScriptVariable
    private Style currentStyle;

    private String headingLevel;

    @SlingObject
    private ResourceResolver resourceResolver;

    private String resolvedAltText;

    @PostConstruct
    protected void init() {
        headingLevel = currentStyle != null
                ? currentStyle.get("headingLevel", "h2")
                : "h2";

        if (!headingLevel.matches("h[1-6]")) {
            headingLevel = "h2";
        }

        resolvedAltText = resolveAltText();
    }

    public String getAnchorId() {
        return anchorId;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroText() {
        return introText;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getAltText() {
        return altText;
    }

    public List<TeaserLink> getLinks() {
        return links;
    }

    public boolean isOpenInNewTab() {
        return openInNewTab;
    }

    public String getTarget() {
        return openInNewTab ? "_blank" : "_self";
    }

    public String getRel() {
        return openInNewTab ? "noopener noreferrer" : "";
    }

    public boolean isShowBackToTop() {
        return showBackToTop;
    }

    public String getHeadingLevel() {
        return headingLevel;
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