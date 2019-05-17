# Weighted Levenshtein distance in Scala

This Scala module implements is a modified version of the Levenshtein distance. More specifically, it is possible to give different costs to letter insertions, deletions and changes depending on the index of the letter in the given word. For example, changing the last letter in a word might have a higher cost than other operations.

Based on the Wagner–Fischer algorithm, the implementation yields the correct result in most of the cases, and seems to be sufficiently accurate for practical applications. However, it does not take into account cases where the shortest path includes a step that has more letters than either of the original words.
