package org.dsa.assignment6

/**
 * Class for Markov chain model with inputted text being turned into a Markov Model. This uses the AssociativeArray
 * class from assignment-6
 */
class MarkovModel(text: String) {
    var arr: AssociativeArray<String, MutableList<Pair<String, Int>>> = AssociativeArray()
    var textList: List<String> = listOf()

    init {
        textList = text.split("\\s+".toRegex())
        println(textList.size)
    }

    /**
     * Create a Markov model for the text provided in the input of the class
     *
     * This function loops through and adds each sequential word to the ongrowing map of next words and counts.
     */
    fun generateModel() {
        for (idx in 0..textList.size - 2) {
            // Check if the word seen is already in the list or not
            if (arr.contains(textList[idx])) {
                // Get list of words that have followed this word along with count
                val wordsFollowing = arr.get(textList[idx])
                var addedWord: Boolean = false
                if (wordsFollowing != null) {
                    // Loop through each word that followed to check for current next word
                    for (followingWord in wordsFollowing) {
                        if (followingWord.first == textList[idx+1]) {
                            // If match found, add 1 to count and update both the list to include new value as well as
                            // set the array to the new list
                            val newTotal = followingWord.second + 1
                            val idxInSmallerList = wordsFollowing.indexOf(followingWord)
                            wordsFollowing.set(idxInSmallerList, Pair(followingWord.first, newTotal))
                            arr.set(textList[idx], wordsFollowing)
                            addedWord = true
                        }
                    }
                }
                // If the next word is not in the current words list, add it to the mutable list
                if (!addedWord) {
                    if (wordsFollowing != null) {
                        wordsFollowing.add(Pair(textList[idx + 1], 1))
                        arr.set(textList[idx], wordsFollowing)
                    }
                }
            }
            // If the word is not in the list already, add it to a new mutable list and add to hashmap array
            else {
                val wordsFollowing: MutableList<Pair<String, Int>> = mutableListOf()
                wordsFollowing.add(Pair(textList[idx + 1], 1))
                arr.set(textList[idx], wordsFollowing)
            }
        }
    }

    /**
     * Print the model as a string
     */
    fun print() {
        println(arr.keyValuePairs())
    }
}


