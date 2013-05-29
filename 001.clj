(comment
	These are my original solution.  They do not fully answer the question, but are saved here for reference.

	(defn sum-multiples
		"Calculates the sum of all of the multiples of step, leading up to target-number"
		([step target-number current-number]
			(if (<= target-number current-number)
				0
				(+ current-number (sum-multiples step target-number (+ current-number step) )))
		)
	)

	(defn sum-multiples-of-3-and-5 
		"Finds the sum of all multiples of 3 and 5 from 0 to target-number"
		( [target-number]
			(+ (sum-multiples 3 target-number 0) (sum-multiples 5 target-number 0))
		)
	)

)

(defn multiples
	"Finds all of the numbers leading up to target-number that are multiples of step"
	([step target-number] (multiples step target-number step))
	([step target-number current-number]
		(if (>= current-number target-number)
			()
			(list* current-number (multiples step target-number (+ current-number step) )))
	)
)

(defn sum-multiples+
	"Calculates the sum of all of the numbers preceding target-number that are multiples of values"
	([target-number & values]
		(apply + (distinct (apply concat (map (fn [step] (multiples step target-number)) values))))
	)
)