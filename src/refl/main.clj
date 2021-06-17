(ns refl.main
  (:require [clojure.java.io :as io]
            [cognitect.aws.http.cognitect :as http]
            [cognitect.aws.client.api :as aws])
  (:gen-class))

(def http-client (delay (http/create)))
(def sqs (delay (aws/client {:api :sqs
                             :http-client @http-client})))

(defn -main [& _]
  (aws/invoke @sqs {:op :SendMessage
                    :request {:QueueUrl "doesn't matter"
                              :MessageBody "doesn't matter"}}))
