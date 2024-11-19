export default function formatToNumAbbrs(num: number): string {
  if (num < 1000) {
    return num.toString();
  }

  const suffixes = ["K", "M", "B", "T"];
  let value = num;
  let i = 0;

  // 숫자를 1000으로 나누며 suffixes 배열에서 적절한 단위를 찾음
  while (value >= 1000 && i < suffixes.length) {
    value /= 1000;
    i++;
  }

  // 소수점 첫째 자리까지 출력하고, 약어 추가
  return value.toFixed(1) + suffixes[i - 1];
}
