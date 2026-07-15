(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Helsinki -- the
  THIRTEENTH municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires for the first twelve) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL hel.fi (City of Helsinki) English-language
  HTML page -- never fabricated. An ordinance not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an id/url/date.

  Stockholm was attempted first this tick: its official KFS PDF
  (kfs-2023-14-allmanna-lokala-ordningsforeskrifter-for-stockholms-kommun.pdf)
  rendered with a font-subsetting artifact severe enough that even the
  document's own title was illegible (only the 'Stockholms stad' header
  and an isolated 'Ändring i 3 § samt bilaga 2' fragment survived), and
  its HTML pages did not expose a clear adoption date -- abandoned
  without forcing it. Helsinki's hel.fi English pages rendered fully
  instead, with explicit in-force dates stated directly in the text.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"helsinki"
   [{:ordinance/id "helsinki.environmental-protection-regulations"
     :ordinance/title "Environmental Protection Regulations of the City of Helsinki"
     :ordinance/municipality "helsinki"
     :ordinance/country "FIN"
     :ordinance/kind :ordinance
     :ordinance/url "https://www.hel.fi/en/urban-environment-and-traffic/protection-of-the-environment-and-nature/prevention-of-environmental-hazards/environmental-protection-regulations"
     :ordinance/url-provenance :official-hel-fi
     :ordinance/enacted-date "2018-07-15"
     :ordinance/last-revised-date "2026-05-15"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:environment}}
    {:ordinance/id "helsinki.administrative-regulations-rules-of-operation"
     :ordinance/title "Administrative Regulations and Rules of Operation of the City of Helsinki"
     :ordinance/municipality "helsinki"
     :ordinance/country "FIN"
     :ordinance/kind :ordinance
     :ordinance/url "https://www.hel.fi/en/decision-making/city-organization/the-administrative-regulations-and-rules-of-operation"
     :ordinance/url-provenance :official-hel-fi
     :ordinance/enacted-date "2017-06-01"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:governance}}]})

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
      :note (str "cloud-itonami-municipality-fin-helsinki Wave 0 (ADR-2607141700): "
                 (count (get catalog "helsinki")) " Helsinki entries seeded "
                 "with an official hel.fi citation. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
