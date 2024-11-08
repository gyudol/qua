import Link from "next/link";

export default function withLink<T>(Component: React.FC<T>) {
  function ComponentWithLink(
    props: { href: string; withoutLink?: boolean } & T,
  ) {
    if (props.withoutLink) return <Component {...props} />;

    return (
      <Link href={props.href}>
        <Component {...props} />
      </Link>
    );
  }
  return ComponentWithLink;
}
