(function () {
    "use strict";
    function init(section) {
        if (section.dataset.accInit === "true") {
            return;
        }
        var toggleAll = section.querySelector("[data-cmp-hook-accordion-all]");
        var accordion = section.querySelector(".cmp-accordion");
        if (!toggleAll || !accordion) {
            return;
        }
        function buttons() {
            return Array.prototype.slice.call(accordion.querySelectorAll('[data-cmp-hook-accordion="button"]'));
        }
        function allOpen() {
            var items = buttons();
            return (
                items.length > 0 &&
                items.every(function (button) {
                    return button.getAttribute("aria-expanded") === "true";
                })
            );
        }
        function sync() {
            var open = allOpen();
            toggleAll.textContent = open ? toggleAll.dataset.collapseLabel : toggleAll.dataset.expandLabel;
            toggleAll.setAttribute("aria-expanded", String(open));
        }
        function toggleAccordion(event) {
            event.preventDefault();
            var scrollX = window.scrollX;
            var scrollY = window.scrollY;
            var items = buttons();
            var open = allOpen();
            items.forEach(function (button) {
                var isExpanded = button.getAttribute("aria-expanded") === "true";
                if (open && isExpanded) {
                    button.click();
                } else if (!open && !isExpanded) {
                    button.click();
                }
            });
            sync();
            if (window.location.hash.startsWith("#accordion-")) {
                history.replaceState(null, "", window.location.pathname + window.location.search);
            }
            toggleAll.focus({
                preventScroll: true,
            });
            window.scrollTo({
                left: scrollX,
                top: scrollY,
                behavior: "instant",
            });
        }
        toggleAll.setAttribute(
            "aria-controls",
            buttons()
                .map(function (button) {
                    return button.getAttribute("aria-controls");
                })
                .filter(Boolean)
                .join(" ")
        );
        toggleAll.addEventListener("click", toggleAccordion);
        var observer = new MutationObserver(function () {
            sync();
        });
        observer.observe(accordion, {
            subtree: true,
            attributes: true,
            attributeFilter: ["aria-expanded"],
        });
        section.dataset.accInit = "true";
        sync();
    }
    function scan(root) {
        var scope = root || document;

        if (scope.matches && scope.matches("[data-cmp-hook-accordion-section]")) {
            init(scope);
        }
        scope.querySelectorAll("[data-cmp-hook-accordion-section]").forEach(init);
    }
    function start() {
        scan();
        var pageObserver = new MutationObserver(function (records) {
            records.forEach(function (record) {
                record.addedNodes.forEach(function (node) {
                    if (node.nodeType === 1) {
                        scan(node);
                    }
                });
            });
        });
        pageObserver.observe(document.body, {
            childList: true,
            subtree: true,
        });
    }
    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", start);
    } else {
        start();
    }
})();
