(comment
	"This is legacy code from my original attempt.  It is preserved here for posterity.  Due to its brute-force nature, it was insufficiently efficient"

	(defn multiples
		([step target-number] (map (fn [number] (* number step)) (range 1 (/ target-number step))))
	)

	(defn non-primes 
		(
			[target-number]
			(distinct
				(apply concat 
					(map
						(fn [multiples-list] (rest multiples-list))
						(map 
							(fn [step] (multiples step target-number)) 
							(range 2 (+ 1 target-number))
						)
					)
				)
			)
		)
	)

	(defn prime?
		"returns whether or not number is prime"
		([number]
			(not (some #{number} (non-primes number)))
		)
		([number non-primes-list]
			(not (some #{number} non-primes-list))
		)
	)

	(defn primes-to
		"Calculates all of the prime numbers below target.  Brute-forces it."
		([target]
			(filter prime? (range 2 target))
		)
		([target non-primes-list]
			(filter #(prime? % non-primes-list) (range 2 target))
		)
	)

	(defn prime-factors
		"Calculates all of the prime factors of target"
		([target]
			(let [non-primes-list (non-primes target)]
				(filter (fn [number] (= 0 (mod target number))) (primes-to target non-primes-list))
			)
		)
	)

)

(comment
	(defn prime?
		([number primes-list]
			(not-any? #(= 0 (mod number %)) primes-list)
		)
	)

	(defn primes-to
		([target]
			(loop [number 2 
				  primes-list []]
				(if (<= number target)
					(if (prime? number primes-list)
						(recur (+ 1 number) (conj primes-list number))
						(recur (+ 1 number) primes-list)
					)
					primes-list
				)
			)
		)
	)
)

(require '[clojure.math.numeric-tower :as math])

(defn sieve-primes
	([number-to]
		(loop 
			[current-number 0
			number-pool (vec (range 2 number-to))]
			(if (= current-number (count number-pool))
				number-pool
				(recur (+ 1 current-number) (remove #(or (zero? (mod % (nth number-pool current-number))) (> % (math/sqrt current-number))) number-pool))
			)
		)
	)
)

(defn prime-factors
	([number]
		(filter (fn [prime] (= 0 (mod number prime))) (sieve-primes (math/sqrt number)))
	)
)
