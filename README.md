# Weighted Levenshtein distance in Scala

This Scala module implements is a more general version of the Levenshtein distance. More specifically, it adds the possibility of assigning different costs to letter insertions, deletions and changes depending on the index of the letter in the given word. For example, changing the last letter of a word might have a higher cost than other operations.

Based on the Wagner–Fischer algorithm, the implementation yields the correct distance in most of the cases, and seems to be sufficiently accurate for practical applications. However, it does not address cases where the shortest path includes a step that has more letters than either of the original words. The errors are usually subtle.
