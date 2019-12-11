class Solution(object):
    def firstMissingPositive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        numMap = {}

        if len(nums) is 0:
            return 1

        for num in nums:
            if num <= len(nums) and num > 0:
                numMap[num] = True

        for i in range(1, len(nums) + 2):
            if i not in numMap:
                return i

