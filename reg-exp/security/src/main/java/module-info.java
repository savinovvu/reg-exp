module security {
    requires spring.context;
    requires servlet.api;
    requires spring.security.core;
    requires spring.security.web;
    requires spring.web;
    requires slf4j.api;
    requires jjwt;
    requires spring.security.config;
    requires spring.beans;
    requires spring.data.jpa;
    requires java.persistence;

    requires common;
}