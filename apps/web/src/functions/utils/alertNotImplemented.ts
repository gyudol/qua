interface AlertNotImplementedArgs {
  message?: string;
  tag?: string;
}

export default function alertNotImplemented({
  message = "아직 구현되지 않은 기능입니다.",
  tag,
}: AlertNotImplementedArgs = {}) {
  // eslint-disable-next-line no-alert -- for test
  alert(`${tag} ${message}`);
}
