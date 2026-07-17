(ns culture.facts
  "Regional-culture catalog for Helsinki -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"helsinki"
   [{:culture/id "helsinki.dish.karelian-pasty"
     :culture/name "Karelian pasty"
     :culture/name-local "Karjalanpiirakka"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :dish
     :culture/summary "Traditional Finnish pasties originating from the region of Karelia, holding traditional speciality guaranteed (TSG) status in Europe since 2003; national rather than Helsinki-specific."
     :culture/url "https://en.wikipedia.org/wiki/Karelian_pasty"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.dish.lohikeitto"
     :culture/name "Salmon soup"
     :culture/name-local "Lohikeitto"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :dish
     :culture/summary "Creamy soup of salmon fillets, boiled potatoes, carrots and leeks, a common dish in Finland and other Nordic countries."
     :culture/url "https://en.wikipedia.org/wiki/Lohikeitto"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.dish.vorschmack"
     :culture/name "Vorschmack"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :dish
     :culture/summary "Minced-meat-and-herring appetizer; Finns consider vorschmack a national dish because it was the favorite appetizer of Marshal Mannerheim."
     :culture/url "https://en.wikipedia.org/wiki/Forshmak"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.beverage.lonkero"
     :culture/name "Long drink"
     :culture/name-local "Lonkero"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :beverage
     :culture/summary "Very popular Finnish mixed drink made from gin and a mixer, traditionally grapefruit soda; national rather than Helsinki-specific."
     :culture/url "https://en.wikipedia.org/wiki/Long_drink"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.product.salmiakki"
     :culture/name "Salty liquorice"
     :culture/name-local "Salmiakki"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :product
     :culture/summary "Confection common in the Nordic countries, Benelux and northern Germany, with Finland prominently associated; national rather than Helsinki-specific."
     :culture/url "https://en.wikipedia.org/wiki/Salty_liquorice"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.product.marimekko"
     :culture/name "Marimekko"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :product
     :culture/summary "Finnish textiles and home-furnishings company founded by Viljo and Armi Ratia in Helsinki in 1951 and headquartered in the city."
     :culture/url "https://en.wikipedia.org/wiki/Marimekko"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.craft.arabia-ceramics"
     :culture/name "Arabia ceramics"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :craft
     :culture/summary "Finnish ceramics company founded in 1873, whose original factory in Toukola, Helsinki specialized in faience and porcelain kitchenware and tableware."
     :culture/url "https://en.wikipedia.org/wiki/Arabia_(brand)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.festival.helsinki-festival"
     :culture/name "Helsinki Festival"
     :culture/name-local "Helsingin juhlaviikot"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :festival
     :culture/summary "Annual multi-arts festival held each August in Helsinki, the largest multi-arts festival in Finland, spanning theatre, music, dance, exhibitions, circus and film."
     :culture/url "https://en.wikipedia.org/wiki/Helsinki_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.heritage.suomenlinna"
     :culture/name "Suomenlinna"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :heritage
     :culture/summary "Sea fortress composed of eight islands about four kilometres southeast of Helsinki's city centre, designated a UNESCO World Heritage Site in 1991."
     :culture/url "https://en.wikipedia.org/wiki/Suomenlinna"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "helsinki.heritage.helsinki-cathedral"
     :culture/name "Helsinki Cathedral"
     :culture/name-local "Helsingin tuomiokirkko"
     :culture/municipality "helsinki"
     :culture/country "FIN"
     :culture/kind :heritage
     :culture/summary "Neoclassical cathedral in Helsinki designed by Carl Ludvig Engel, built 1830-1852, with a tall green dome surrounded by four smaller domes."
     :culture/url "https://en.wikipedia.org/wiki/Helsinki_Cathedral"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-fin-helsinki culture catalog "
                 "(ADR-2607171400): " (count (get catalog "helsinki"))
                 " Helsinki entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
