(ns app.todo-list
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

#?(:clj (defonce !text (atom "# Your document

- major point 1
- major point 2

_for glory!_")))
(e/def text (e/server (e/watch !text)))

(comment
  (reset! !text "INSANE STUFF 4"))

(e/defn Todo-list []
  (e/client
   (dom/link (dom/props {:rel :stylesheet :href "/todo-list.css"}))
   (dom/link (dom/props {:rel :stylesheet :href "/pandoc-preview.css"}))
   (dom/h1 (dom/text "Commonmark editor"))
   (dom/p (dom/em (dom/text "powered by Pandoc and Electric Clojure")))
   (dom/textarea (dom/props {:class "input-textfield"})
                 (dom/on "input" (e/fn [e]
                                   (when-some [v (.. e -target -value)]
                                     (e/server (reset! !text v))))))
   (dom/div (dom/text text))))
