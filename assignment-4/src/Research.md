## MinFinder Sorting Algorithm

Link: https://www.sciencedirect.com/science/article/pii/S1877050919307896

The way this algorithm sorts the list is by incrementally searching the list to find the minimum
value and then moving it to the beginning of the list by swapping the elements in front of it to
the right one by one. It then finds the next value in the list and brings it to the second
position in the list by similarly moving the preceding values to the right one by one. In this way,
the algorithm completes the sort in n^2 time.
