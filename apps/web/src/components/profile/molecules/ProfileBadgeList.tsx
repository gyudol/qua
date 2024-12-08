import {
  ProfileBadge,
  ProfileBadgeAdd,
  ProfileBadgeMore,
} from "../atoms/ProfileBadge";

export function ProfileBadgeList() {
  const badges = [1, 2, 3, 4];
  return (
    <ul className="flex p-[1rem] gap-[0.75rem] flex-wrap">
      <li>
        <ProfileBadgeAdd />
      </li>
      {badges.map((v) => (
        <li key={v}>
          <ProfileBadge />
        </li>
      ))}
      <li>
        <ProfileBadgeMore />
      </li>
    </ul>
  );
}
