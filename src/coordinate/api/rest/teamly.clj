(ns com.coordinate.api.rest.teamly
  (:use [liberator.core :only [defresource]]))

(defresource update-status
  :available-media-types ["text/plain"]
  :handle-ok "Good")

