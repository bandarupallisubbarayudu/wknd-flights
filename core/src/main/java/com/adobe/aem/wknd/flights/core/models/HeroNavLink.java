package com.adobe.aem.wknd.flights.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeroNavLink {

    @ValueMapValue
    private String linkText;

    @ValueMapValue  
    private String linkId;

    public String getLinkText() {
        return linkText;
    }

    public String getLinkId() {
        return linkId;
    }

}