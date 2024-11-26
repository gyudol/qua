interface PostedAtProps {
  postedAt: string;
}

interface PostedAtParam {
  unit: string;
  num: number;
}

function _postedAt({ unit, num }: PostedAtParam) {
  const flooredNum = Math.floor(num);
  const _unit = unit + (flooredNum === 1 ? '' : 's');

  return `${flooredNum} ${_unit} ago`;
}

export default function PostedAt({ postedAt }: PostedAtProps) {
  const updated = new Date(postedAt);
  const now = new Date();
  const diff = now.getTime() - updated.getTime();
  const diffSec = diff / 1000;
  const diffMin = diffSec / 60;
  const diffHour = diffMin / 60;
  const diffDay = diffHour / 24;
  const diffMonth = diffDay / 30;
  const diffYear = diffMonth / 12;

  let text = '';

  switch (true) {
    case diffYear >= 1:
      text = _postedAt({ unit: 'year', num: diffYear });
      break;
    case diffMonth >= 1:
      text = _postedAt({ unit: 'month', num: diffMonth });
      break;
    case diffDay >= 1:
      text = _postedAt({ unit: 'day', num: diffDay });
      break;
    case diffHour >= 1:
      text = _postedAt({ unit: 'hour', num: diffHour });
      break;
    case diffMin >= 1:
      text = _postedAt({ unit: 'minute', num: diffMin });
      break;
    default:
      text = 'Just now';
  }

  return <p className="text-gray-400 text-xs">{text}</p>;
}
