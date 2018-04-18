package com.feg.socialnetwork;

/**
 * Created by Simon Premstaller on 21.03.2018.
 */

public class API {

    public static final String ROOT_URL = "http://137.74.140.21/Webservice/API.php?call=";

    public static final String URL_REGISTER = ROOT_URL + "register";
    public static final String URL_LOGIN = ROOT_URL + "login";
    public static final String URL_INSERTPOST = ROOT_URL + "post";
    public static final String URL_GETPOSTS = ROOT_URL + "getpost";
    public static final String URL_GETPOSTS_ONLY_USER = ROOT_URL + "getpost&userposts=true";
    public static final String URL_FOLLOW = ROOT_URL + "follow";
    public static final String URL_IS_FOLLOWING = ROOT_URL + "following";

    public static final int CODE_GET_REQUEST = 1024;
    public static final int CODE_POST_REQUEST = 1025;

}
