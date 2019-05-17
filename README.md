# Weighted Levenshtein distance in Scala

This Scala module implements is a modified version of the Levenshtein distance. More specifically, the algorithm allows weighting letter insertions, deletions and changes depending on the index of the letter in the given word. For example, changing the last letter in a word might have a higher cost than other operations.

The approach has some caveats. The algorithm does not take into account cases where the shortest path includes a step that has more letters than either of the original words. However, the obtained distance seems to be sufficiently accurate for most applications.
