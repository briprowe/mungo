(ns mungo.core
  (:use [hiccup.core]
        [compojure.core]
        [mungo.application-info :only [fb-app-info]])
  (:require [appengine-magic.core :as ae]))

(defn load-fb-js [app-id]
  (format "<div id=\"fb-root\"></div>
<script>
  window.fbAsyncInit = function() {
    var appId = '%s';
    FB.init({appId: appId, status: true, cookie: true,
             xfbml: true});
    FB.getLoginStatus(function(response) {  });
    mungo.init(FB, appId);
  };
  (function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol +
      '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
  }());
</script>" app-id))

(defn load-scripts []
  (let [script-tag "%s<script type=\"text/javascript\" src=\"%s\"></script>\n"
        scripts ["http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.js"
                 "/js/pics.js" "/js/mungo.js"]]
    (reduce #(format script-tag %1 %2) "" scripts)))

(defroutes all
  (ANY "/canvas/*" []  (html [:html
                              [:head ]
                              [:body (load-scripts)
                               (load-fb-js (:app-id fb-app-info))]]))
  (POST "/login-info/:token" [token]
        (fn [token] (println token))))

(ae/def-appengine-app mungo-app #'all)
