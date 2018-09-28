module common {
    exports ru.inbox.savinov_vu.common.interfaces;
    exports ru.inbox.savinov_vu.common.constant;
    exports ru.inbox.savinov_vu.common.interfaces.CRUD;
    exports ru.inbox.savinov_vu.common.interfaces.numbered;

    opens ru.inbox.savinov_vu.common.interfaces;
    requires spring.data.commons;
    requires spring.web;
    requires spring.tx;
    requires spring.data.jpa;
    requires servlet.api;
}