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

(defn prime-factors
	([number]
		(filter (fn [prime] (= 0 (mod number prime))) (primes-to number))
	)
)

(defn multiples
	([number]
		(loop [factors (prime-factors number)]
			(if (= number (apply * factors))
				factors
				(recur (concat (factors) (/ number (apply * factors))))
			)
		)
	)
)


(comment
(defn smallest-multiple
	([upper-bound]
		(loop [multiples (primes-to upper-bound)]
			(if )
		)
	)
)
)