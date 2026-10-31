package com.adobe.aem.wknd.flights.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;


@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TeaserLink {

    @ValueMapValue
    private String linkText;

    @ValueMapValue
    private String linkURL;

    @PostConstruct
    protected void init() {
        linkURL = resolveLink(linkURL);
    }

    private String resolveLink(String path) {
        if (path == null || path.isBlank()) {
            return "";
        }

        if (path.startsWith("http://")
                || path.startsWith("https://")
                || path.startsWith("mailto:")
                || path.startsWith("tel:")
                || path.startsWith("#")) {
            return path;
        }

        if (!path.startsWith("/content/") || path.endsWith(".html")) {
            return path;
        }

        int queryIndex = path.indexOf('?');
        int hashIndex = path.indexOf('#');
        int suffixIndex = -1;

        if (queryIndex >= 0 && hashIndex >= 0) {
            suffixIndex = Math.min(queryIndex, hashIndex);
        } else if (queryIndex >= 0) {
            suffixIndex = queryIndex;
        } else if (hashIndex >= 0) {
            suffixIndex = hashIndex;
        }

        if (suffixIndex >= 0) {
            String pagePath = path.substring(0, suffixIndex);
            String suffix = path.substring(suffixIndex);

            return pagePath + ".html" + suffix;
        }

        return path + ".html";
    }

    public String getLinkText() {
        return linkText;
    }

    public String getLinkURL() {
        return linkURL;
    }
}