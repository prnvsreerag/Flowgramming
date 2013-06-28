/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

/**
 *
 * @author Ryan
 */
public class LinkModel {
    private String caption;
    private Class expectedType;

    private LinkModel(String caption, Class expectedType) {
        this.caption = caption;
        this.expectedType = expectedType;
    }

    public String getCaption() {
        return this.caption;
    }

    public Class getExpectedType() {
        return this.expectedType;
    }

    public static LinkModelBuilder builder() {
        return new LinkModelBuilder();
    }

    public static class LinkModelBuilder {

        private Class type;
        private String caption;

        public LinkModelBuilder expectingType(Class type) {
            this.type = type;
            return this;
        }

        public LinkModelBuilder withCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public LinkModel build() {
            return new LinkModel(this.caption, this.type);
        }
    }
}
