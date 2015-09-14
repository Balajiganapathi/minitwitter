function userLoggedIn() {
    sessionId = Cookies.get("sessionId");
    return sessionId != null;
}