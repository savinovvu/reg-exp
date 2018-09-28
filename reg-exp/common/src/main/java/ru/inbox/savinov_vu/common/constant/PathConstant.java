package ru.inbox.savinov_vu.common.constant;

public class PathConstant {

    private PathConstant() {
    }


    public static final String USER = "/users/user";

    public static final String REGEXP_TASK = "/tasks/regexptask";

    public static final String REGEXP_LEVEL = "/tasks/regexplevel";

    public static final String LIKE = "/tasks/like";

    public static final String COMMENT = "/tasks/comment";

    public static final String NUMBERED = "/byNumber";

    public static final String NUMBERED_WITH_PARAM = NUMBERED + "/{number}";

    public static final String NUMBERED_WITH_PARENT_PARAM = NUMBERED + "/{parentNumber}/{number}";

    public static final String PARENT = "parent";

    public static final String ID = "{id}";

    public static final String PARENT_PARAM = PARENT + "/" + ID;

    public static final String SIGN_IN = "/signin";

    public static final String SIGN_UP = "/signup";

    public static final String BACK_END_PATHS = "/back-end-paths";

}
