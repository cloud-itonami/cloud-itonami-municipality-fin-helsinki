# cloud-itonami-municipality-fin-helsinki

Municipal-ordinance compliance catalog for **Helsinki** — the
THIRTEENTH municipality-level entry alongside
[`cloud-itonami-municipality-jpn-tokyo`](https://github.com/cloud-itonami/cloud-itonami-municipality-jpn-tokyo),
[`cloud-itonami-municipality-usa-washington-dc`](https://github.com/cloud-itonami/cloud-itonami-municipality-usa-washington-dc),
[`cloud-itonami-municipality-gbr-london`](https://github.com/cloud-itonami/cloud-itonami-municipality-gbr-london),
[`cloud-itonami-municipality-can-toronto`](https://github.com/cloud-itonami/cloud-itonami-municipality-can-toronto),
[`cloud-itonami-municipality-deu-berlin`](https://github.com/cloud-itonami/cloud-itonami-municipality-deu-berlin),
[`cloud-itonami-municipality-fra-paris`](https://github.com/cloud-itonami/cloud-itonami-municipality-fra-paris),
[`cloud-itonami-municipality-nld-amsterdam`](https://github.com/cloud-itonami/cloud-itonami-municipality-nld-amsterdam),
[`cloud-itonami-municipality-esp-madrid`](https://github.com/cloud-itonami/cloud-itonami-municipality-esp-madrid),
[`cloud-itonami-municipality-kor-seoul`](https://github.com/cloud-itonami/cloud-itonami-municipality-kor-seoul),
[`cloud-itonami-municipality-ita-roma`](https://github.com/cloud-itonami/cloud-itonami-municipality-ita-roma),
[`cloud-itonami-municipality-aus-sydney`](https://github.com/cloud-itonami/cloud-itonami-municipality-aus-sydney),
and
[`cloud-itonami-municipality-arg-buenos-aires`](https://github.com/cloud-itonami/cloud-itonami-municipality-arg-buenos-aires).
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on the City of
Helsinki's behalf.

Coverage is reported honestly (see `ordinance.facts/coverage`): a
municipality not in `catalog` has **no spec-basis**, full stop — never
fabricate one.

## Data

- `src/ordinance/facts.cljc` — the catalog, source of truth.
- `schema/ordinance.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (query this
  alongside other `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`).

Stockholm was attempted first: its official KFS PDF rendered with a
font-subsetting artifact severe enough that even the document's own
title was illegible, and its HTML pages did not expose a clear adoption
date — abandoned without forcing it.
[hel.fi](https://www.hel.fi/en)'s English-language pages rendered fully
instead: the **Environmental Protection Regulations of the City of
Helsinki** (in force since 2018-07-15, amended 2026-05-15) and the
**Administrative Regulations and Rules of Operation of the City of
Helsinki** (in force since 2017-06-01).

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Ordinance text
itself remains the City of Helsinki's; this repo stores only citation
metadata (id/title/url/dates), not full text.
