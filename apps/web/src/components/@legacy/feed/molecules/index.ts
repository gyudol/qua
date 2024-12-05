import { withLink } from "@/components/common/atoms";
import FeedButtonGroup from "./FeedButtonGroup";
import FeedContent from "./FeedContent";
import FeedHeader from "./FeedHeader";
import FeedMediaContainer from "./FeedMediaContainer";

const FeedContentWithLink = withLink(FeedContent);
const FeedMediaContainerWithLink = withLink(FeedMediaContainer);

export {
  FeedButtonGroup,
  FeedContent,
  FeedHeader,
  FeedMediaContainer,
  FeedContentWithLink,
  FeedMediaContainerWithLink,
};
