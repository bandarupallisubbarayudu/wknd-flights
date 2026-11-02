(function () {
    "use strict";
    if ("scrollRestoration" in history) {
        history.scrollRestoration = "manual";
    }
    function removeHashAndScrollToTop() {
        if (window.location.hash) {
            history.replaceState(
                null,
                "",
                window.location.pathname + window.location.search
            );
        }
        window.scrollTo({
            top: 0,
            left: 0,
            behavior: "smooth"
        });
    }
    window.addEventListener("load", removeHashAndScrollToTop);
    window.addEventListener("pageshow", removeHashAndScrollToTop);
})();